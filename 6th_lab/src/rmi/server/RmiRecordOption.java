package rmi.server;

import recording.comparator.CompositionComparator;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * RMI interface to server instance
 *
 * @author Ihar Zharykau
 */
public interface RmiRecordOption extends Remote{
    void writeOnDisk(Collection<? extends Composition> pCompositions) throws RemoteException;
    CompositionDuration durationOfWrittenOnDisk() throws RecordingException, RemoteException;
    void sort(CompositionComparator pComparator) throws RemoteException;
    void sort(CompositionComparator pComparator, boolean reverse) throws RemoteException;
    List<Composition> find(Map<String, Object> pParameters) throws RemoteException;
    List<Composition> getCompositions() throws RemoteException;
    void writeCompositionOnDisk(Composition composition) throws RemoteException;
}
