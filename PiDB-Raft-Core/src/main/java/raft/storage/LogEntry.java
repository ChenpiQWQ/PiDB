package raft.storage;

import com.google.protobuf.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import raft.RaftMessageReceiver;
import rpc.RaftProto;

public class LogEntry {
     private final RaftProto.Entry entry;
     private static final Logger LOG = LoggerFactory.getLogger(LogEntry.class);
     public LogEntry(LogAction action, String key, int value, int term, int index) {
         switch (action) {
             case GET:
                 entry = RaftProto.Entry.newBuilder().setAction(RaftProto.Action.GET).setKey(key).setTerm(term).setIndex(index).build();
                 break;
             case PUT:
                 entry = RaftProto.Entry.newBuilder().setAction(RaftProto.Action.PUT).setKey(key).setValue(value).setTerm(term).setIndex(index).build();
                 break;
             default:
                 entry = null;
                 LOG.error("Only GET or PUT action for LogEntry.");
         }
     }

     public RaftProto.Entry getEntry() {
         return entry;
     }
}
