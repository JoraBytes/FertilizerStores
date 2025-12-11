package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class HsnBasicsQuery {

	public String save() {
		StringBuilder bldr = new StringBuilder();
		bldr.append("Insert Into hsnbasics ( \n");
		bldr.append("hsncode,hsnname,companytag,companyflag, \n");
		bldr.append("sgst,cgst,igst,active,createdby,createddate, \n");
		bldr.append("createdtime \n");
		bldr.append(") Values (\n");
		bldr.append(":hsncode,:hsnname,:companytag,:companyflag,\n ");
		bldr.append(":sgst,:cgst,:igst,:active,:createdby,now(),\n ");
		bldr.append("now()\n ");
		bldr.append(") ");
		return bldr.toString();
	}

	public String viewHsnBasics() {
		StringBuilder bldr = new StringBuilder();
		bldr.append(
				"select h.hsncode,h.hsnname,h.sgst,h.cgst,h.igst,h.active,o.operatorname as createdby,date_format(h.createddate,'%d-%b-%Y')createddate,m.operatorname as modifyby,date_format(h.modifieddate,'%d-%b-%Y') modifieddate,h.companyflag,h.companytag from hsnbasics as h\n ");
		bldr.append("left join operator o on o.operatorcode=h.createdby\n ");
		bldr.append("left join operator m on m.operatorcode=h.modifyby;\n ");
		return bldr.toString();
	}

}
