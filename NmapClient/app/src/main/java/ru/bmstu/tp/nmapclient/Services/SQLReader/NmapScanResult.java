package ru.bmstu.tp.nmapclient.Services.SQLReader;

import java.util.Date;
import java.util.List;

public class NmapScanResult {

    private String args;
    private Date start;
    private List<Host> hosts;

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public static class Host {

        private Date starttime;
        private Address address;
        private List<HostName> hostNames;
        private List<Port> ports;
        private String os;
        private String trace;

        public Date getStarttime() {
            return starttime;
        }

        public void setStarttime(Date starttime) {
            this.starttime = starttime;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<HostName> getHostNames() {
            return hostNames;
        }

        public void setHostNames(List<HostName> hostNames) {
            this.hostNames = hostNames;
        }

        public List<Port> getPorts() {
            return ports;
        }

        public void setPorts(List<Port> ports) {
            this.ports = ports;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getTrace() {
            return trace;
        }

        public void setTrace(String trace) {
            this.trace = trace;
        }
    }


    public static class Address {

        private String addr;
        private String addrtype;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAddrtype() {
            return addrtype;
        }

        public void setAddrtype(String addrtype) {
            this.addrtype = addrtype;
        }
    }


    public static class HostName {

        private String name;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    public static class Port {

        private String protocol;
        private String portId;
        private String content;

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getPortId() {
            return portId;
        }

        public void setPortId(String portId) {
            this.portId = portId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
