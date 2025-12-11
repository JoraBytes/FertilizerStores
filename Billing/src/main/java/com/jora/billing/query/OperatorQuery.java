package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class OperatorQuery {

	public String save() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into operator(operatorname,password,active,prouser,\n");
		sb.append("entrymode,viewmode,editmode,deletemode,cancelaccess,\n");
		sb.append("rateentry,previousdateentry,companytag,companyflag,autogen)\n");
		sb.append("values");
		sb.append("(:operatorname,:password,:active,:prouser,\n");
		sb.append(":entrymode,:viewmode,:editmode,:deletemode,:cancelaccess,\n");
		sb.append(":rateentry,:previousdateentry,:companytag,:companyflag,:autogen)\n");
		return sb.toString();
	}

	public String view() {
		StringBuilder sb = new StringBuilder();
		sb.append("select operatorcode,operatorname,password,active,prouser,\n");
		sb.append("entrymode,viewmode,editmode,deletemode,cancelaccess,\n");
		sb.append("rateentry,previousdateentry,companytag,companyflag,autogen\n");
		sb.append("from operator");
		return sb.toString();
	}

	public String update() {
		StringBuilder sb = new StringBuilder();
		sb.append("update operator set operatorname=:operatorname,password=:password,\n");
		sb.append("active=:active,prouser=:prouser,entrymode=:entrymode,\n");
		sb.append("viewmode=:viewmode,editmode=:editmode,deletemode=:deletemode,\n");
		sb.append("cancelaccess=:cancelaccess,rateentry=:rateentry,previousdateentry=:previousdateentry,\n");
		sb.append("companytag=:companytag,companyflag=:companyflag,\n");
		sb.append("modifieddatetime=CURRENT_TIMESTAMP,modifiedby=:modifiedby \n");
		sb.append("where operatorcode=:operatorcode");
		return sb.toString();
	}

}
