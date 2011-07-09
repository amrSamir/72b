package com.OJToolkit.server.engine;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * @author OmarEl-Mohandes
 *
 */
public interface Judge {

    /**
     * @param filePath : The filePath that contains all the urls of the problems.
     * @return : All the problems texts
     * @throws Exception
     */
    public ArrayList<ProblemText> getProblemTexts(String filePath)throws Exception;
    /**
     *
     * @param username : Username of the coder.
     * @param password : Password of the coder.
     * @return boolean : <b>True<b> if signed in successfully , <n>False<n> if not.
     */
    public boolean signIn(String username, String password) throws Exception;

    /**
     *
     * @param username : Username of the coder.
     * @return <b>True<b> if signed in successfully , <n>False<n> if not.
     */
    public boolean signOut(String username);

    /**
     *
     * @param coderId :  The ID of the coder.
     * @param password : The password of the coder.
     * @param problemId :The ID of the problem .
     * @param language : The ID of the language in this online judge.
     * @param code :     The submitted code.
     * @throws IOException
     * @throws Exception
     */
    public void submitProblem(String coderId, String password, String problemId, String languageId, String code) throws IOException, Exception;

    /**
     * @param coderId : The ID of the coder.
     * @param password : The password of the user.
     * @return Instance of type Submission of the last submitted problem.
     * @throws Exception
     */
    public Submission getLastSubmission(String coderId, String password) throws Exception;

    /**
     *
     * @return All the problems on the online judge , instances of type Problem.
     * @throws Exception
     */
    public ArrayList<Problem> getAllProblems() throws Exception;

    /**
     *
     * @param codeId : The coder ID.
     * @return All the problems solved by this coder.
     * @throws Exception
     */
    public ArrayList<String> getProblemsSolved(String coderId) throws MalformedURLException, Exception;

    /**
     *
     * @param problemId : The problem ID.
     * @return Instance of type Problem.
     * @throws Exception
     */
    public Problem getProblemInfo(String problemId) throws Exception;
}

