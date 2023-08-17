package model;

public class Jobs {
    private long id;
    private String jobTitle;
    private float ratePerHour;
    private int contactNum;
    private String areaLocated;

    private long user_id;

    public Jobs () {

    }

    public Jobs(String jobTitle,float ratePerHour,int contactNum,String areaLocated) {
        this.jobTitle=jobTitle;
        this.ratePerHour=ratePerHour;
        this.contactNum=contactNum;
        this.areaLocated=areaLocated;

    }
    public Jobs(long id,String jobTitle,float ratePerHour,int contactNum,String areaLocated,long user_id) {
        this.id=id;
        this.jobTitle=jobTitle;
        this.ratePerHour=ratePerHour;
        this.contactNum=contactNum;
        this.areaLocated=areaLocated;
        this.user_id=user_id;

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public float getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(float ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public int getContactNum() {
        return contactNum;
    }

    public void setContactNum(int contactNum) {
        this.contactNum = contactNum;
    }

    public String getAreaLocated() {
        return areaLocated;
    }

    public void setAreaLocated(String areaLocated) {
        this.areaLocated = areaLocated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", ratePerHour=" + ratePerHour +
                ", contactNum=" + contactNum +
                ", areaLocated='" + areaLocated + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
