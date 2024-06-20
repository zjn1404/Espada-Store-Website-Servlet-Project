package model.product;

import java.util.Objects;

public class Type {
	
	private String typeId;
	private String typeName;
	
	public Type() {}
	
	public Type(String typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName; // top, bottom, accessory
	}
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(typeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return Objects.equals(typeId, other.typeId);
	}

	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
	
}
