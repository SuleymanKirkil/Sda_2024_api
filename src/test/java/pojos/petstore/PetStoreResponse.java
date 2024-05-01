package pojos.petstore;

import java.util.List;

public class PetStoreResponse {
	private List<String> photoUrls;
	private String name;
	private Integer id;
	private Category category;
	private List<TagsItem> tags;
	private String status;

	public PetStoreResponse() {
	}

	public PetStoreResponse(List<String> photoUrls, String name, Integer id, Category category, List<TagsItem> tags, String status) {
		this.photoUrls = photoUrls;
		this.name = name;
		this.id = id;
		this.category = category;
		this.tags = tags;
		this.status = status;
	}

	public void setPhotoUrls(List<String> photoUrls){
		this.photoUrls = photoUrls;
	}

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"PetStoreResponse{" +
			"photoUrls = '" + photoUrls + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			",tags = '" + tags + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}