package name.orionis.helper.reflection;
/**
 * Controller Method Information
 * @author code.404
 *
 */
public class ActionMethodInfo implements Comparable<ActionMethodInfo> {
	private String belong;
	private String methodName;
	private String [] url;
	private String remark;
	private String group;
	
	public String getBelong() {
		return belong;
	}
	public String getMethodName() {
		return methodName;
	}
	public String[] getUrl() {
		return url;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public void setUrl(String[] url) {
		this.url = url;
	}
	public String getSimpleClassName(){
		return belong.substring(belong.lastIndexOf(".") + 1, belong.length());
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public int compareTo(ActionMethodInfo o) {
		return group.compareToIgnoreCase(o.getGroup());
	}
	
	
}
