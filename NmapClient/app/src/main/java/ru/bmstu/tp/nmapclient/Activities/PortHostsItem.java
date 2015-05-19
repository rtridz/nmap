package ru.bmstu.tp.nmapclient.Activities;

public class PortHostsItem {

    public String domain;
    public String port;
    public String server1;
    public String server2;

    public PortHostsItem(String _domain, String _port, String _server1, String _server2) {
        domain = _domain;
        port = _port;
        server1 = _server1;
        server2 = _server2;
    }
}
