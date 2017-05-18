package nicole.durability.actions;

import nicole.durability.*;

public class ShowPaperAction implements MenuAction {

	private PaperInterface paper;

	public ShowPaperAction(PaperInterface paper) {
		this.paper = paper;
	}

	@Override
	public String getMenuTextToDisplay() {
		return "Show paper";
	}

	@Override
	public void perform() {
		this.paper.showPaper();
	}

	public PaperInterface getPaper() {
		return this.paper;
	}

}
