package matc.edu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceDataItem {

	@JsonProperty("PUMS Industry")
	private String pUMSIndustry;

	@JsonProperty("ID PUMS Industry")
	private String iDPUMSIndustry;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("ID PUMA")
	private String iDPUMA;

	@JsonProperty("Total Population")
	private int totalPopulation;

	@JsonProperty("Record Count")
	private int recordCount;

	@JsonProperty("Slug PUMS Industry")
	private String slugPUMSIndustry;

	@JsonProperty("PUMA")
	private String pUMA;

	@JsonProperty("Slug PUMA")
	private String slugPUMA;

	@JsonProperty("ygipop RCA")
	private double ygipopRCA;

	@JsonProperty("ID Year")
	private int iDYear;

	public void setPUMSIndustry(String pUMSIndustry){
		this.pUMSIndustry = pUMSIndustry;
	}

	public String getPUMSIndustry(){
		return pUMSIndustry;
	}

	public void setIDPUMSIndustry(String iDPUMSIndustry){
		this.iDPUMSIndustry = iDPUMSIndustry;
	}

	public String getIDPUMSIndustry(){
		return iDPUMSIndustry;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setIDPUMA(String iDPUMA){
		this.iDPUMA = iDPUMA;
	}

	public String getIDPUMA(){
		return iDPUMA;
	}

	public void setTotalPopulation(int totalPopulation){
		this.totalPopulation = totalPopulation;
	}

	public int getTotalPopulation(){
		return totalPopulation;
	}

	public void setRecordCount(int recordCount){
		this.recordCount = recordCount;
	}

	public int getRecordCount(){
		return recordCount;
	}

	public void setSlugPUMSIndustry(String slugPUMSIndustry){
		this.slugPUMSIndustry = slugPUMSIndustry;
	}

	public String getSlugPUMSIndustry(){
		return slugPUMSIndustry;
	}

	public void setPUMA(String pUMA){
		this.pUMA = pUMA;
	}

	public String getPUMA(){
		return pUMA;
	}

	public void setSlugPUMA(String slugPUMA){
		this.slugPUMA = slugPUMA;
	}

	public String getSlugPUMA(){
		return slugPUMA;
	}

	public void setYgipopRCA(double ygipopRCA){
		this.ygipopRCA = ygipopRCA;
	}

	public double getYgipopRCA(){
		return ygipopRCA;
	}

	public void setIDYear(int iDYear){
		this.iDYear = iDYear;
	}

	public int getIDYear(){
		return iDYear;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"pUMS Industry = '" + pUMSIndustry + '\'' + 
			",iD PUMS Industry = '" + iDPUMSIndustry + '\'' + 
			",year = '" + year + '\'' + 
			",iD PUMA = '" + iDPUMA + '\'' + 
			",total Population = '" + totalPopulation + '\'' + 
			",record Count = '" + recordCount + '\'' + 
			",slug PUMS Industry = '" + slugPUMSIndustry + '\'' + 
			",pUMA = '" + pUMA + '\'' + 
			",slug PUMA = '" + slugPUMA + '\'' + 
			",ygipop RCA = '" + ygipopRCA + '\'' + 
			",iD Year = '" + iDYear + '\'' + 
			"}";
		}
}