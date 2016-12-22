package rmi.server;

import recording.comparator.CompositionComparator;
import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.exception.RecordingException;
import recording.options.RecordOptions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * RMI interface realization. This class is {@link RecordOptions} class wrapper for RMI purpose
 *
 * @author Ihar Zharykau
 */
public class RmiRecordOptionImpl extends UnicastRemoteObject implements RmiRecordOption {
    private RecordOptions recordOptions = new RecordOptions();

    public RmiRecordOptionImpl() throws RemoteException {
        super();
    }

    @Override
    public void writeOnDisk(Collection<? extends Composition> pCompositions) throws RemoteException {
        recordOptions.writeOnDisk(pCompositions);
    }

    @Override
    public CompositionDuration durationOfWrittenOnDisk() throws RecordingException, RemoteException {
        return recordOptions.durationOfWrittenOnDisk();
    }

    @Override
    public void sort(CompositionComparator pComparator) throws RemoteException {
        recordOptions.sort(pComparator);
    }

    @Override
    public void sort(CompositionComparator pComparator, boolean reverse) throws RemoteException {
        recordOptions.sort(pComparator, reverse);
    }

    @Override
    public List<Composition> find(Map<String, Object> pParameters) throws RemoteException {
        return recordOptions.find(pParameters);
    }

    @Override
    public List<Composition> getCompositions() throws RemoteException {
        return recordOptions.getCompositions();
    }

    @Override
    public void writeCompositionOnDisk(Composition composition) throws RemoteException {
        recordOptions.writeCompositionOnDisk(composition);
    }
}
