package name.orionis.helper.reflection;
import java.util.List;
/**
 * The Controller Information
 * @author code.404
 *
 */
public class ControllerClassInfo implements Comparable<ControllerClassInfo> {
	private String className;
	private Class<?> clazz;
	private String remark;
	private List<ActionMethodInfo> methods;
	private String group;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ActionMethodInfo> getMethods() {
		return methods;
	}
	public void setMethods(List<ActionMethodInfo> methods) {
		this.methods = methods;
	}
	public void addMethod(ActionMethodInfo methodInfo){
		this.methods.add(methodInfo);
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public int compareTo(ControllerClassInfo o) {
		return group.compareToIgnoreCase(o.getGroup());
	}
	
}
