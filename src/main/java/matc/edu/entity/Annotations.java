package matc.edu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Annotations{

	@JsonProperty("topic")
	private String topic;

	@JsonProperty("hidden_measures")
	private String hiddenMeasures;

	@JsonProperty("dataset_name")
	private String datasetName;

	@JsonProperty("dataset_link")
	private String datasetLink;

	@JsonProperty("table_id")
	private String tableId;

	@JsonProperty("source_name")
	private String sourceName;

	@JsonProperty("subtopic")
	private String subtopic;

	@JsonProperty("source_description")
	private String sourceDescription;

	public void setTopic(String topic){
		this.topic = topic;
	}

	public String getTopic(){
		return topic;
	}

	public void setHiddenMeasures(String hiddenMeasures){
		this.hiddenMeasures = hiddenMeasures;
	}

	public String getHiddenMeasures(){
		return hiddenMeasures;
	}

	public void setDatasetName(String datasetName){
		this.datasetName = datasetName;
	}

	public String getDatasetName(){
		return datasetName;
	}

	public void setDatasetLink(String datasetLink){
		this.datasetLink = datasetLink;
	}

	public String getDatasetLink(){
		return datasetLink;
	}

	public void setTableId(String tableId){
		this.tableId = tableId;
	}

	public String getTableId(){
		return tableId;
	}

	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}

	public String getSourceName(){
		return sourceName;
	}

	public void setSubtopic(String subtopic){
		this.subtopic = subtopic;
	}

	public String getSubtopic(){
		return subtopic;
	}

	public void setSourceDescription(String sourceDescription){
		this.sourceDescription = sourceDescription;
	}

	public String getSourceDescription(){
		return sourceDescription;
	}

	@Override
 	public String toString(){
		return 
			"Annotations{" + 
			"topic = '" + topic + '\'' + 
			",hidden_measures = '" + hiddenMeasures + '\'' + 
			",dataset_name = '" + datasetName + '\'' + 
			",dataset_link = '" + datasetLink + '\'' + 
			",table_id = '" + tableId + '\'' + 
			",source_name = '" + sourceName + '\'' + 
			",subtopic = '" + subtopic + '\'' + 
			",source_description = '" + sourceDescription + '\'' + 
			"}";
		}
}