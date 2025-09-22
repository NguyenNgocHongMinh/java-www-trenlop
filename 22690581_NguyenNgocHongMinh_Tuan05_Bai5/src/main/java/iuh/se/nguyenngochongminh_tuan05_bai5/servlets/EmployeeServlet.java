package iuh.se.nguyenngochongminh_tuan05_bai5.servlets;
import iuh.se.nguyenngochongminh_tuan05_bai5.model.Employee;
import iuh.se.nguyenngochongminh_tuan05_bai5.dao.DepartmentDAO;
import iuh.se.nguyenngochongminh_tuan05_bai5.dao.EmployeeDAO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    @Resource(name="jdbc/companies")
    private DataSource dataSource;
    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            empDao = new EmployeeDAO(dataSource);
            deptDao = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        switch (action) {
            case "list":
                 // Load toàn bộ employees (không quan tâm deptId)
                List<Employee> allEmployees = empDao.getAllEmployees();
                req.setAttribute("employees", allEmployees);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
            case "new":
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Employee emp = empDao.getByID(id);
                req.setAttribute("employee", emp);
                req.setAttribute("departments", deptDao.getAllDepartments());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;
            case "delete":
                empDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees");
                break;
            case "viewbyid": // list all employees (or by department)
                String deptId = req.getParameter("deptId");
                List<Employee> list;

                if (deptId != null) {
                    list = empDao.getAllByDepartment(Integer.parseInt(deptId));
                } else {
                    list = empDao.getAllByDepartment(1); // mặc định dept 1
                }
                req.setAttribute("employees", list);
                req.setAttribute("departments", deptDao.getAllDepartments());

                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        int id;
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
        } else {
            id = 0;
        }

        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        Employee emp = new Employee(id, name, deptDao.getByID(deptId), salary);
        if (id > 0) {
            empDao.update(emp);
        } else {
            empDao.save(emp);
        }
        resp.sendRedirect("employees?deptId=" + deptId);
    }
}
