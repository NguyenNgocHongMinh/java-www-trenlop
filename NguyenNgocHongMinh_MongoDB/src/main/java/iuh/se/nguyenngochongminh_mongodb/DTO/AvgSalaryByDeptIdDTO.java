package iuh.se.nguyenngochongminh_mongodb.DTO;

public class AvgSalaryByDeptIdDTO {
    private String deptId;
    private long count;
    private double avgSalary;

    public AvgSalaryByDeptIdDTO(String deptId, long count, double avgSalary) {
        this.deptId = deptId;
        this.count = count;
        this.avgSalary = avgSalary;
    }

    public AvgSalaryByDeptIdDTO() {
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }
}
