package model;

public class Jobs {
    private long id;
    private String jobTitle;
    private String ratePerHour;
    private String contactNum;
    private String areaLocated;

    private long user_id;

    public Jobs () {

    }

    public Jobs(String jobTitle,String ratePerHour,String contactNum,String areaLocated) {
        this.jobTitle=jobTitle;
        this.ratePerHour=ratePerHour;
        this.contactNum=contactNum;
        this.areaLocated=areaLocated;

    }
    public Jobs(long id,String jobTitle,String ratePerHour,String contactNum,String areaLocated,long user_id) {
        this.id=id;
        this.jobTitle=jobTitle;
        this.ratePerHour=ratePerHour;
        this.contactNum=contactNum;
        this.areaLocated=areaLocated;
        this.user_id=user_id;

    }

    public Jobs(String jobTitle, String jobRatePerHour, String jobContact, String jobArea, long userId) {
        this.jobTitle=jobTitle;
        this.ratePerHour=jobRatePerHour;
        this.contactNum=jobContact;
        this.areaLocated=jobArea;
        this.user_id=userId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(String ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
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
