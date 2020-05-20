/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.dtos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "Subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s")
    , @NamedQuery(name = "Subject.findBySubjectId", query = "SELECT s FROM Subject s WHERE s.subjectId = :subjectId")
    , @NamedQuery(name = "Subject.findByNumberOfQuestions", query = "SELECT s FROM Subject s WHERE s.numberOfQuestions = :numberOfQuestions")
    , @NamedQuery(name = "Subject.findBySubjectName", query = "SELECT s FROM Subject s WHERE s.subjectName = :subjectName")
    , @NamedQuery(name = "Subject.findByTimeTest", query = "SELECT s FROM Subject s WHERE s.timeTest = :timeTest")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SubjectId")
    private String subjectId;
    @Basic(optional = false)
    @Column(name = "NumberOfQuestions")
    private int numberOfQuestions;
    @Basic(optional = false)
    @Column(name = "SubjectName")
    private String subjectName;
    @Basic(optional = false)
    @Column(name = "TimeTest")
    private int timeTest;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<Question> questionCollection;

    public Subject() {
    }

    public Subject(String subjectId) {
        this.subjectId = subjectId;
    }

    public Subject(String subjectId, int numberOfQuestions, String subjectName, int timeTest) {
        this.subjectId = subjectId;
        this.numberOfQuestions = numberOfQuestions;
        this.subjectName = subjectName;
        this.timeTest = timeTest;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTimeTest() {
        return timeTest;
    }

    public void setTimeTest(int timeTest) {
        this.timeTest = timeTest;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "linhnd.daos.Subject[ subjectId=" + subjectId + " ]";
    }
    
}
