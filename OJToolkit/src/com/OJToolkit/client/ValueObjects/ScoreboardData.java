package com.OJToolkit.client.ValueObjects;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreboardData {

	static ContestData contest;

	ArrayList<CoderData> coders;
	ArrayList<ProblemData> problems;
	ArrayList<SubmissionData> submissions;
	
	ArrayList<Integer> problemAC ;
	ArrayList<Integer> problemWA ;

	@SuppressWarnings("unchecked")
	public ArrayList<ScoreBoardRow> loadScoreboardRows() {
		ArrayList<ScoreBoardRow> rows = new ArrayList<ScoreBoardRow>();
		for (CoderData cd : coders) {
			ScoreBoardRow row = new ScoreBoardRow(cd);
			for (SubmissionData sd : submissions) {
				if (sd.getUsername().equals(cd.getUsername())) {
					row.addSubmission(sd);
				}
			}
			rows.add(row);
		}
		Collections.sort(rows);
		return rows;
	}

	public String get(String problemCode,String problemOJ,boolean b){
		int i = 0 ;		
		for(ProblemData pd : problems){
			if(pd.getOjType().equals(problemOJ)&&pd.getProblemCode().equals(problemCode))
				if(b)
					return String.valueOf(problemAC.get(i));
				else 
					return String.valueOf(problemWA.get(i)+problemAC.get(i));
			i++ ;
		}
		return "0" ;
	}
	
	public ContestData getContest() {
		return contest;
	}

	public void setContest(ContestData contest) {
		this.contest = contest;
		
	}

	public ArrayList<CoderData> getCoders() {
		return coders;
	}

	public void setCoders(ArrayList<CoderData> coders) {
		
		this.coders = coders;
	}

	public ArrayList<ProblemData> getProblems() {
		return problems;
	}

	public void setProblems(ArrayList<ProblemData> problems) {
		
		this.problems = problems;
		problemAC = new ArrayList<Integer>() ;
		problemWA = new ArrayList<Integer>() ;
		for(int i = 0 ; i < problems.size() ; i++){
			problemAC.add(0) ;
			problemWA.add(0) ;
		}
	}

	public ArrayList<SubmissionData> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(ArrayList<SubmissionData> submissions) {
		this.submissions = submissions;
		loadproblemStat();
	}

	private void loadproblemStat() {
		problemAC.clear() ;
		problemWA.clear() ;
		for(int i = 0 ; i < problems.size() ; i++){
			problemAC.add(0) ;
			problemWA.add(0) ;
		}
		int i = 0;
		for (ProblemData problem : problems) {
			for (SubmissionData sd : submissions) {
				if (problem.getProblemCode().equals(sd.getProblemCode()) && problem.getOjType().equals(sd.getJudgeType())) {
					if (sd.getJudgeResult().equalsIgnoreCase("accepted")) {
						problemAC.set(i, problemAC.get(i)+1);
					}else
						problemWA.set(i, problemWA.get(i)+1);
				}
			}
			i++;
		}
	}

	public ScoreboardData() {
		contest = new ContestData() ;
		coders = new ArrayList<CoderData>();
		problems = new ArrayList<ProblemData>();
		submissions = new ArrayList<SubmissionData>();
		problemAC = new ArrayList<Integer>();
		problemWA = new ArrayList<Integer>();
	}

}
