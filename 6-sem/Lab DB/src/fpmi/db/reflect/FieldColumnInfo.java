package fpmi.db.reflect;

/**
 * @author Ihar Zharykau
 */
public class FieldColumnInfo {
    private String fieldName;
    private String columnName;
    private Class fieldClass;

    public FieldColumnInfo() {
    }

    public FieldColumnInfo(String fieldName, String columnName, Class fieldClass) {
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.fieldClass = fieldClass;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(Class fieldClass) {
        this.fieldClass = fieldClass;
    }

    @Override
    public String toString() {
        return "FieldColumnInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", fieldClass=" + fieldClass +
                '}';
    }
}
