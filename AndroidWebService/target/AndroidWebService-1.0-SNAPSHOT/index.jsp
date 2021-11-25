<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.webservice.androidwebservice.ItemService"%>
<%@page import="javax.inject.Named"%>
<%@page import="com.webservice.androidwebservice.ItemsResource"%>
<%@page import="javax.ejb.EJB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%! 
    /*@EJB
    ItemsResource items;
    @Named
    public void jspInit() {
        try {
            InitialContext ic = new InitialContext();
            ItemsResource items = (ItemsResource) ic.lookup("java:global/AndroidWebService/ItemsResource");
        }
        catch(Exception e) {
        }
    }*/
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <p1>
        This Web Service is currently designed for android users
    </p1>
    <br>
    <br>
    <p1>
        This Web Service is currently in development for Browser use
    </p1>
    <form method="GET" action="/AndroidWebService/rest/webservice/android/all">
        <button>
            Android Service for JSON (Test):
        </button>
    </form>
    </body>
</html>
