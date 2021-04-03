package matc.edu.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {

	@JsonProperty("data")
	private List<DataItem> data;

	@JsonProperty("source")
	private List<SourceItem> source;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSource(List<SourceItem> source){
		this.source = source;
	}

	public List<SourceItem> getSource(){
		return source;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"data = '" + data + '\'' + 
			",source = '" + source + '\'' + 
			"}";
		}
}