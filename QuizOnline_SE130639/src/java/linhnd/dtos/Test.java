/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.dtos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;
    @Basic(optional = false)
    @Column(name = "TestTitle")
    private String testTitle;
    @Basic(optional = false)
    @Column(name = "Score")
    private double score;
    @Basic(optional = false)
    @Column(name = "CreateDate")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @OneToMany(mappedBy = "testId", cascade = CascadeType.PERSIST)
    private Collection<TestQuestions> testQuestionsCollection;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne(optional = false)
    private Account email;

    public Test() {
    }

    public Test(Integer testId) {
        this.testId = testId;
    }

    public Test(Integer testId, String testTitle, double score, Date createDate) {
        this.testId = testId;
        this.testTitle = testTitle;
        this.score = score;
        this.createDate = createDate;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCreateDate() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(createDate);
        return date;
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
        return "linhnd.dtos.Test[ testId=" + testId + " ]";
    }

}
