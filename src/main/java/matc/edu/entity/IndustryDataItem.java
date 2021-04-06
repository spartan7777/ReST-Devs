package matc.edu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IndustryDataItem {
    @JsonProperty("ID Industry Group")
    private String iDIndustryGroup;
    @JsonProperty("Industry Group")
    private String industryGroup;
    @JsonProperty("ID Year")
    private int iDYear;
    @JsonProperty("Year")
    private int year;
    @JsonProperty("Average Wage")
    private double averageWage;
    @JsonProperty("Slug Industry Group")
    private String slugIndustryGroup;

    public String getiDIndustryGroup() {
        return iDIndustryGroup;
    }

    public void setiDIndustryGroup(String iDIndustryGroup) {
        this.iDIndustryGroup = iDIndustryGroup;
    }

    public String getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
    }

    public int getiDYear() {
        return iDYear;
    }

    public void setiDYear(int iDYear) {
        this.iDYear = iDYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAverageWage() {
        return averageWage;
    }

    public void setAverageWage(double averageWage) {
        this.averageWage = averageWage;
    }

    public String getSlugIndustryGroup() {
        return slugIndustryGroup;
    }

    public void setSlugIndustryGroup(String slugIndustryGroup) {
        this.slugIndustryGroup = slugIndustryGroup;
    }

    @Override
    public String toString() {
        return "IndustryDataItem{" +
                "iDIndustryGroup='" + iDIndustryGroup + '\'' +
                ", industryGroup='" + industryGroup + '\'' +
                ", iDYear=" + iDYear +
                ", year=" + year +
                ", averageWage=" + averageWage +
                ", slugIndustryGroup='" + slugIndustryGroup + '\'' +
                '}';
    }
}
//"ID Industry Group": "8121M",
//        "Industry Group": "Nail salons & other personal care services",
//        "ID Year": 2019,
//        "Year": "2019",
//        "Average Wage": 17399.597270337254,
//        "Slug Industry Group": "nail-salons-other-personal-care-services"