package com.OJToolkit.client.view;

import java.util.ArrayList;

import com.OJToolkit.client.ValueObjects.CoderData;
import com.OJToolkit.client.ValueObjects.ContestData;
import com.OJToolkit.client.ValueObjects.ProblemData;
import com.OJToolkit.client.ValueObjects.SubmissionData;
import com.OJToolkit.client.presenter.ViewContestPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ViewContestView extends Composite implements ViewContestPresenter.Display{

	ListBox listContests ;
	ListBox listCoders ;
	ListBox listProblems ;
	ListBox listSubmissions ;
	Button btnJoinContest ;
	int contestsSize ;
	ArrayList<ContestData> contests ;
	ArrayList<CoderData> coders ;
	ArrayList<ProblemData> problems ;
	ArrayList<SubmissionData> submissions ;
	VerticalPanel verticalPanel ;
	
	//
	HorizontalPanel codersAndProblems ;
	
	public ViewContestView() {
		coders = new ArrayList<CoderData>() ;
		problems = new ArrayList<ProblemData>();
		contests = new ArrayList<ContestData>();
		submissions = new ArrayList<SubmissionData>() ;
		verticalPanel = new VerticalPanel();
		verticalPanel
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setStyleName("center");
		initWidget(verticalPanel); 
		
		HorizontalPanel pnlJoinContest = new HorizontalPanel();
		Label lblContestName = new Label("choose contest");
		listContests = new ListBox() ;
		pnlJoinContest.add(lblContestName);
		pnlJoinContest.add(listContests) ;
		
		verticalPanel.add(pnlJoinContest);
		
		
		HorizontalPanel pnlAccessCodeContest = new HorizontalPanel();
		
		verticalPanel.add(pnlAccessCodeContest);
		
		btnJoinContest = new Button("view contest");
		btnJoinContest.setSize("100px", "28px");
		verticalPanel.add(btnJoinContest) ;
		verticalPanel.setCellHorizontalAlignment(btnJoinContest,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		
		codersAndProblems = new HorizontalPanel();
		verticalPanel.add(codersAndProblems) ;
		listCoders = new ListBox() ;
		listProblems = new ListBox() ;
		listSubmissions = new ListBox() ;
		codersAndProblems.add(listCoders) ;
		codersAndProblems.add(listProblems) ;
		codersAndProblems.add(listSubmissions) ;
		
		
	}
	
	
	
	@Override
	public void setCoders(ArrayList<CoderData> coders) {
		this.coders = coders ;
		viewCoders();
	}

	private void viewCoders() {
		listCoders.clear();
		for(CoderData c:coders){
			listCoders.addItem(c.getUsername()) ;
		}
	}



	@Override
	public void setProblems(ArrayList<ProblemData> problems) {
		this.problems= problems ;
		viewProblems();
	}

	private void viewProblems() {
		listProblems.setVisibleItemCount(Math.max(coders.size(),problems.size())) ;
		listProblems.clear() ;
		for(ProblemData c:problems){
			listProblems.addItem(c.getProblemName()) ;
		}
	}

	@Override
	public void setContests(ArrayList<ContestData> contests) {
		this.contests = contests ;
		System.out.println("Magdi-presnter-join- Number of contests = " + contests.size());
		listContests.clear() ;
		for(int i = 0 ; i < contests.size() ; i++){
			listContests.addItem(contests.get(i).getContestName());
		}
	}
	
	@Override
	public void setSubmissions(ArrayList<SubmissionData> submissions) {
		this.submissions = submissions ;
		listSubmissions.clear() ;
		for(SubmissionData sd : submissions){
			listSubmissions.addItem(sd.getUsername()+" "+sd.getProblemTitle()+" results:"+sd.getJudgeResult()) ;
		}
	}


	@Override
	public HasClickHandlers getSubmitButton() {
		return btnJoinContest;
	}

	@Override
	public String getContestName() {
		return listContests.getValue(listContests.getSelectedIndex());
	}



	

}
