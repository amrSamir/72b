/**
 * 
 */
package com.OJToolkit.server.engine;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author omar
 */
public class ProblemText {
        private String problemStatement;
        private String inputConstraints;
        private String outputConstraints;
        private String ioTestCases;
        private String allProblem;
        public boolean isSplitted;

        public ProblemText() {
        }

        public ProblemText(String problemStatement, String inputConstraints,
                        String outputConstraints, String ioTestCases ,  boolean is, String all) {
                this.problemStatement = problemStatement;
                this.inputConstraints = inputConstraints;
                this.outputConstraints = outputConstraints;
                this.ioTestCases = ioTestCases;
                this.allProblem = all;
                this.isSplitted = is;
        }

        public String getAllProblem() {
                return allProblem;
        }

        public void setAllProblem(String allProblem) {
                this.allProblem = allProblem;
        }

        public String getInputConstraints() {
                return inputConstraints;
        }

        public void setInputConstraints(String inputConstraints) {
                this.inputConstraints = inputConstraints;
        }

        public String getIOTestCases() {
                return ioTestCases;
        }

        public void setIOTestCases(String inputTestCases) {
                this.ioTestCases = inputTestCases;
        }

        public String getOutputConstraints() {
                return outputConstraints;
        }

        public void setOutputConstraints(String outputConstraints) {
                this.outputConstraints = outputConstraints;
        }

        public String getProblemStatement() {
                return problemStatement;
        }

        public void setProblemStatement(String problemStatement) {
                this.problemStatement = problemStatement;
        }

}


