package matc.edu.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceItem{

	@JsonProperty("measures")
	private List<String> measures;

	@JsonProperty("substitutions")
	private List<Object> substitutions;

	@JsonProperty("name")
	private String name;

	@JsonProperty("annotations")
	private Annotations annotations;

	public void setMeasures(List<String> measures){
		this.measures = measures;
	}

	public List<String> getMeasures(){
		return measures;
	}

	public void setSubstitutions(List<Object> substitutions){
		this.substitutions = substitutions;
	}

	public List<Object> getSubstitutions(){
		return substitutions;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAnnotations(Annotations annotations){
		this.annotations = annotations;
	}

	public Annotations getAnnotations(){
		return annotations;
	}

	@Override
 	public String toString(){
		return 
			"SourceItem{" + 
			"measures = '" + measures + '\'' + 
			",substitutions = '" + substitutions + '\'' + 
			",name = '" + name + '\'' + 
			",annotations = '" + annotations + '\'' + 
			"}";
		}
}