package ru.bmstu.tp.nmapclient.Services.Exceptions;

/*
    Session Id can be deleted on a server, if account isn't actives for a long time
     or GCM Id of this Session Id was cleaning by Google
 */
public class BadSessionIdException extends Exception {
}
