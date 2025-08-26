package leetCode.java8;

import java.util.List;

public class TypeDto {
    private String typeSeq;
    private String type;
    private String typeName;
    private Integer sort;
    private List<String> subTypeList;

    public String getTypeSeq() {
        return typeSeq;
    }

    public void setTypeSeq(String typeSeq) {
        this.typeSeq = typeSeq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<String> getSubTypeList() {
        return subTypeList;
    }

    public void setSubTypeList(List<String> subTypeList) {
        this.subTypeList = subTypeList;
    }
}
