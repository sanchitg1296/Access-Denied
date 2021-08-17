package com.ieteisf.iete_try1;

public class faq {

    private String Question;
    private String Answer;

    faq(){}
    
    public faq(String Question,String Answer){
        this.Answer = Answer;
        this.Question = Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public void setQuestion(String question) {
        this.Question = question;
    }
}
