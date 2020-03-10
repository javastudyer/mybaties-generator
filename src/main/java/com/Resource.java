//package com;
//
//class Resource{
//    private String name;
//    private int resource;
//
//    public Resource(String name, int resource) {
//        this.name = name;
//        this.resource = resource;
//    }
//    synchronized void doSome(){
//        System.out.println("doing some thing");
//        resource++;
//    }
//    synchronized void cooperate(Resource resource){
//        System.out.println("cooperating");
//        resource.doSome();
//    }
//}
