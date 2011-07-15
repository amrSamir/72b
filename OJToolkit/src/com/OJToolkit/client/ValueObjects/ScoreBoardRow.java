package com.OJToolkit.client.ValueObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ScoreBoardRow implements Comparable {
	CoderData coder;
	ArrayList<SubmissionData> coderSubmission;
	ArrayList<ProblemData> solved = new ArrayList<ProblemData>();
	int AC;
	Long plenty = 20L * 60000L;
	Long totaltime = 0L;

	public Long getPlenty() {
		return plenty;
	}

	public void setPlenty(Long plenty) {
		this.plenty = plenty;
	}

	public int getAC() {
		return AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}

	public ScoreBoardRow() {
		plenty = 20L * 60000L;
		AC = 0;
		totaltime = 0L;
		coder = new CoderData();
		coderSubmission = new ArrayList<SubmissionData>();
		solved = new ArrayList<ProblemData>();
	}

	public ScoreBoardRow(CoderData coder) {
		AC = 0;
		this.coder = coder;
		coderSubmission = new ArrayList<SubmissionData>();
	}

	public CoderData getCoder() {
		return coder;
	}

	public void setCoder(CoderData coder) {
		this.coder = coder;
	}

	public ArrayList<SubmissionData> getCoderSubmission() {
		return coderSubmission;
	}

	public void setCoderSubmission(ArrayList<SubmissionData> coderSubmission) {
		this.coderSubmission = coderSubmission;
	}

	public void addSubmission(SubmissionData sd) {
		if (sd.getJudgeResult().equalsIgnoreCase("accepted")) {
			if (!findProblem(new ProblemData(sd.getProblemCode(), "", "",sd.getJudgeType())))
				solved.add(new ProblemData(sd.getProblemCode(), "", "", sd.getJudgeType()));
			AC++;
			String s = sd.getDate();
			Date d = new Date(s);
			Long time = d.getTime()
					- ScoreboardData.contest.getStartTime().getTime();
			time /= 1000L;
			totaltime += time;
		}
		coderSubmission.add(sd);
	}

	private boolean findProblem(ProblemData problemData) {

		for (ProblemData s : solved) {
			if (s.getOjType().equals(problemData.getOjType())
					&& s.getProblemCode().equals(problemData.getProblemCode()))
				return true;
		}
		return false;
	}

	public String getSubmission(String problemCode, String problemOJ,
			Date startDate) {
		int numberAC = 0;
		int numberWA = 0;
		String SubmissionTime = "";
		Long WrongTime = 0L;
		boolean t = false;
		Long time = 0L;
		for (SubmissionData sd : coderSubmission) {
			if (sd.getProblemCode().equals(problemCode)
					&& sd.getJudgeType().equals(problemOJ)) {
				// return "results: "+sd.getJudgeResult() ;
				if (sd.getJudgeResult().equalsIgnoreCase("accepted")) {
					t = true;
					numberAC++;
					String s = sd.getDate();
					Date d = new Date(s);
					time = d.getTime() - startDate.getTime();
					time /= 1000L;
					break;
				} else {
					numberWA++;
					WrongTime += plenty ;
				}
			}
		}
		if (t) {
			time += WrongTime;
			totaltime += time;
			Long min = time / 60L;
			Long sec = time % 60L;
			SubmissionTime += String.valueOf(min) + ":" + String.valueOf(sec);
		}
		if (numberAC == 0 && numberWA == 0)
			return ".";
		else if (numberAC == 0 && numberWA != 0)
			return "-" + String.valueOf(numberWA);
		else if (numberWA == 0 && numberAC == 1)
			return "+" + "(" + SubmissionTime + ")";
		else
			return "+" + String.valueOf(numberWA + 1) + "(" + SubmissionTime + ")";
	}

	@Override
	public int compareTo(Object o) {
		ScoreBoardRow r = (ScoreBoardRow) o;
		int d = AC - r.AC;
		if (d != 0)
			return d;
		Long l = totaltime - r.totaltime;
		return Integer.parseInt(String.valueOf(l));
	}

	public String getTotaltime() {
		Long min = totaltime / 60L;
		Long sec = totaltime % 60L;
		return String.valueOf(min) + ":" + String.valueOf(sec);
	}

	public void setTotaltime(Long totaltime) {
		this.totaltime = totaltime;
	}
}
