/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "Question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findByQuestionId", query = "SELECT q FROM Question q WHERE q.questionId = :questionId")
    , @NamedQuery(name = "Question.findByCreateDate", query = "SELECT q FROM Question q WHERE q.createDate = :createDate")
    , @NamedQuery(name = "Question.findByQuestionContent", query = "SELECT q FROM Question q WHERE q.questionContent = :questionContent")
    , @NamedQuery(name = "Question.findByCorrectAnswerID", query = "SELECT q FROM Question q WHERE q.correctAnswerID = :correctAnswerID")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "QuestionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    @Basic(optional = false)
    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "QuestionContent")
    private String questionContent;
    @Basic(optional = false)
    @Column(name = "CorrectAnswerID")
    private String correctAnswerID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<Answer> answerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Collection<TestQuestions> testQuestionsCollection;
    @JoinColumn(name = "StatusId", referencedColumnName = "StatusId")
    @ManyToOne(optional = false)
    private Status statusId;
    @JoinColumn(name = "SubjectId", referencedColumnName = "SubjectId")
    @ManyToOne(optional = false)
    private Subject subjectId;

    public Question() {
    }

    public Question(Integer questionId) {
        this.questionId = questionId;
    }

    public Question(Integer questionId, Date createDate, String questionContent, String correctAnswerID) {
        this.questionId = questionId;
        this.createDate = createDate;
        this.questionContent = questionContent;
        this.correctAnswerID = correctAnswerID;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getCorrectAnswerID() {
        return correctAnswerID;
    }

    public void setCorrectAnswerID(String correctAnswerID) {
        this.correctAnswerID = correctAnswerID;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @XmlTransient
    public Collection<TestQuestions> getTestQuestionsCollection() {
        return testQuestionsCollection;
    }

    public void setTestQuestionsCollection(Collection<TestQuestions> testQuestionsCollection) {
        this.testQuestionsCollection = testQuestionsCollection;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "linhnd.daos.Question[ questionId=" + questionId + " ]";
    }
    
}
