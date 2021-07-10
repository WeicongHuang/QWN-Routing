package topo;

public class link {
	int linkID;
	int width;
	boolean use_state;
	double success_rate;
	boolean create_success;
	int realWidth=0;
	public boolean isCreate_success() {
		return create_success;
	}

	public void setCreate_success(boolean create_success) {
		this.create_success = create_success;
	}

	public link(int linkID, int width, boolean use_state, double success_rate) {

		this.linkID = linkID;
		this.width = width;
		this.use_state = use_state;
		this.success_rate = success_rate;
		this.create_success = false;
	}

	public int getLinkID() {
		return linkID;
	}

	public void setLinkID(int linkID) {
		this.linkID = linkID;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public int getRealWidth() {
		return realWidth;
	}

	public void setRealWidth(int realWidth) {
		this.realWidth = realWidth;
	}
	public boolean getUse_state() {
		return use_state;
	}

	public void setUse_state(boolean use_state) {
		this.use_state = use_state;
	}

	public double getSuccess_rate() {
		return success_rate;
	}

	public void setSuccess_rate(double success_rate) {
		this.success_rate = success_rate;
	}

}
