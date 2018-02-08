package com.fs.comm.model;

import java.util.Date;

public class AnswerEntity {
	private int id;
	
	private String answerForm;
	
	private int deflg;
	
	private String problemid;
	
    private String answer;

    private Date createtime;

    private String createname;
    
    private Date updatetime;

    private String updatename;

    private String relationname;

    private String relation;

    private String recommend;

    private String suggest;
    
    private String categoryName;
    
    private String question;
    
    private String derive;

    private String bugs;

    private String reason;
    
    private String qcate;

    private String industry;
    
    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDerive() {
		return derive;
	}

	public void setDerive(String derive) {
		this.derive = derive;
	}

	public String getBugs() {
		return bugs;
	}

	public void setBugs(String bugs) {
		this.bugs = bugs;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getQcate() {
		return qcate;
	}

	public void setQcate(String qcate) {
		this.qcate = qcate;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname == null ? null : relationname.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProblemid() {
		return problemid;
	}

	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdatename() {
		return updatename;
	}

	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}

	public int getDeflg() {
		return deflg;
	}

	public void setDeflg(int deflg) {
		this.deflg = deflg;
	}

	public String getAnswerForm() {
		return answerForm;
	}

	public void setAnswerForm(String answerForm) {
		this.answerForm = answerForm;
	}
}