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
@Table(name = "Test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")
    , @NamedQuery(name = "Test.findByTestId", query = "SELECT t FROM Test t WHERE t.testId = :testId")
    , @NamedQuery(name = "Test.findByTestTitle", query = "SELECT t FROM Test t WHERE t.testTitle = :testTitle")
    , @NamedQuery(name = "Test.findByScore", query = "SELECT t FROM Test t WHERE t.score = :score")
    , @NamedQuery(name = "Test.findByCreateDate", query = "SELECT t FROM Test t WHERE t.createDate = :createDate")})
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TestId")
    private String testId;
    @Basic(optional = false)
    @Column(name = "TestTitle")
    private String testTitle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Score")
    private Double score;
    @Basic(optional = false)
    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testId")
    private Collection<TestQuestions> testQuestionsCollection;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Account email;

    public Test() {
    }

    public Test(String testId) {
        this.testId = testId;
    }

    public Test(String testId, String testTitle, Date createDate) {
        this.testId = testId;
        this.testTitle = testTitle;
        this.createDate = createDate;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @XmlTransient
    public Collection<TestQuestions> getTestQuestionsCollection() {
        return testQuestionsCollection;
    }

    public void setTestQuestionsCollection(Collection<TestQuestions> testQuestionsCollection) {
        this.testQuestionsCollection = testQuestionsCollection;
    }

    public Account getEmail() {
        return email;
    }

    public void setEmail(Account email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testId != null ? testId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.testId == null && other.testId != null) || (this.testId != null && !this.testId.equals(other.testId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "linhnd.daos.Test[ testId=" + testId + " ]";
    }
    
}
