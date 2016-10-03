package com.db.hibernate.options;

import com.db.hibernate.entity.CompositionsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import recording.entity.composition.Composition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by igor on 3.10.16.
 */
public class DBCompositionFactory {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public List<Composition> getAllCompositions() {
        final Session session = getSession();
        List<Composition> compositions = new ArrayList<>();
        try {
            Query query = session.createQuery("from " + CompositionsEntity.class.getSimpleName());
            List<CompositionsEntity> entities = query.list();
            for (CompositionsEntity entity : entities) {
                compositions.add(entity.generateComposition());
            }
        } finally {
            session.close();
        }
        return compositions;
    }

    public List<CompositionsEntity> getAllCompositionEntities() {
        final Session session = getSession();
        List<CompositionsEntity> compositions = new ArrayList<>();
        try {
            Query query = session.createQuery("from " + CompositionsEntity.class.getSimpleName());
            compositions = query.list();
        } finally {
            session.close();
        }
        return compositions;
    }


}
