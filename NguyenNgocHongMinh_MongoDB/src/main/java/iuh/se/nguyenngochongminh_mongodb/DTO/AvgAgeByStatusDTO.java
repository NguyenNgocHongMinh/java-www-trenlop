package iuh.se.nguyenngochongminh_mongodb.DTO;

public class AvgAgeByStatusDTO {
    private int status;
    private long count;
    private double avgAge;

    public AvgAgeByStatusDTO(int status, long count, double avgAge) {
        this.status = status;
        this.count = count;
        this.avgAge = avgAge;
    }

    public AvgAgeByStatusDTO() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getAvgAge() {
        return avgAge;
    }

    public void setAvgAge(double avgAge) {
        this.avgAge = avgAge;
    }
}