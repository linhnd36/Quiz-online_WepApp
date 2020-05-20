/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.dtos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "TestQuestions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestQuestions.findAll", query = "SELECT t FROM TestQuestions t")
    , @NamedQuery(name = "TestQuestions.findByTestQuestionsId", query = "SELECT t FROM TestQuestions t WHERE t.testQuestionsId = :testQuestionsId")})
public class TestQuestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TestQuestionsId")
    private Integer testQuestionsId;
    @JoinColumn(name = "AnswerId", referencedColumnName = "AnswerId")
    @ManyToOne(optional = false)
    private Answer answerId;
    @JoinColumn(name = "QuestionId", referencedColumnName = "QuestionId")
    @ManyToOne(optional = false)
    private Question questionId;
    @JoinColumn(name = "TestId", referencedColumnName = "TestId")
    @ManyToOne(optional = false)
    private Test testId;

    public TestQuestions() {
    }

    public TestQuestions(Integer testQuestionsId) {
        this.testQuestionsId = testQuestionsId;
    }

    public Integer getTestQuestionsId() {
        return testQuestionsId;
    }

    public void setTestQuestionsId(Integer testQuestionsId) {
        this.testQuestionsId = testQuestionsId;
    }

    public Answer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Answer answerId) {
        this.answerId = answerId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testQuestionsId != null ? testQuestionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestQuestions)) {
            return false;
        }
        TestQuestions other = (TestQuestions) object;
        if ((this.testQuestionsId == null && other.testQuestionsId != null) || (this.testQuestionsId != null && !this.testQuestionsId.equals(other.testQuestionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "linhnd.daos.TestQuestions[ testQuestionsId=" + testQuestionsId + " ]";
    }
    
}
