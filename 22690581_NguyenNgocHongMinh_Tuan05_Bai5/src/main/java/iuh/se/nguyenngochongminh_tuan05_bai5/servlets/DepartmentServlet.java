package iuh.se.nguyenngochongminh_tuan05_bai5.servlets;

import iuh.se.nguyenngochongminh_tuan05_bai5.dao.DepartmentDAO;
import iuh.se.nguyenngochongminh_tuan05_bai5.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    @Resource(name="jdbc/companies")
    private DataSource dataSource;

    private DepartmentDAO deptDao;

    @Override
    public void init() {
        deptDao = new DepartmentDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.setAttribute("department", new Department());
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Department dept = deptDao.getByID(id);
                req.setAttribute("department", dept);
                req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                break;
            case "delete":
                deptDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("departments");
                break;
            case "search":
                String keyword = req.getParameter("keyword");
                List<Department> searchResult = deptDao.searchByName(keyword);
                req.setAttribute("departments", searchResult);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;
            default:
                List<Department> departments = deptDao.getAllDepartments();
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String name = req.getParameter("name");

        Department dept = new Department();
        dept.setName(name);

        if (idParam == null || idParam.isEmpty()|| "0".equals(idParam)) {
            deptDao.save(dept);
        } else {
            dept.setId(Integer.parseInt(idParam));
            deptDao.update(dept);
        }

        resp.sendRedirect("departments");

    }
}
