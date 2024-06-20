package model.product;

import java.util.Objects;

public class SubType {
	private Type type;
	private String subTypeId;
	private String subTypeName; // tee, shirt, jacket, hoodie, pants, skirt, shorts, headwear, bag
	
	public SubType() {}
	
	public SubType(Type type, String subTypeId, String subTypeName) {
		super();
		this.type = type;
		this.subTypeId = subTypeId;
		this.subTypeName = subTypeName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(String subTypeId) {
		this.subTypeId = subTypeId;
	}

	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(subTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubType other = (SubType) obj;
		return Objects.equals(subTypeId, other.subTypeId);
	}

	@Override
	public String toString() {
		return "SubType [type=" + type + ", subTypeId=" + subTypeId + ", subTypeName=" + subTypeName + "]";
	}
	
}
