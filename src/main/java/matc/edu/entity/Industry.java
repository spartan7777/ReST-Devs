package matc.edu.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Industry{

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
			"Industry{" + 
			"data = '" + data + '\'' + 
			",source = '" + source + '\'' + 
			"}";
		}
}