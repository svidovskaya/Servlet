package servlets;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.PrintWriter;

public class MyTag extends SimpleTagSupport {
   public String log ="";

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public void doTag() throws JspException, IOException {
      log = "tag";
      setLog(log);
      getJspContext().getOut().print(getLog());
        //System.out.println(log);

    }
}
