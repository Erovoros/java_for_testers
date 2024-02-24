package model;

public record GroupData(String name, String header, String footer) {
    
    public GroupData() {
        this("", "", "");
    }

    public GroupData withName(String someName) {
        return new GroupData(name, this.header, this.footer);
    }

    public GroupData withHeader(String someName) {
        return new GroupData(this.name, header, this.footer);
    }


    public GroupData withFooter(String someName) {
        return new GroupData(this.name, this.header, footer);
    }





}