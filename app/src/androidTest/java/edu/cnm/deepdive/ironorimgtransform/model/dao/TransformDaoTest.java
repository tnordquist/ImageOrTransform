package edu.cnm.deepdive.ironorimgtransform.model.dao;

import static org.junit.Assert.*;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import edu.cnm.deepdive.ironorimgtransform.model.TransformDB;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Transform;
import edu.cnm.deepdive.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransformDaoTest {

  private TransformDB transformDB;
  private TransformDao transformDao;

  @Before
  public void setUp() throws Exception {
    Context context = InstrumentationRegistry.getTargetContext();
    transformDB = Room.inMemoryDatabaseBuilder(context, TransformDB.class).build();
    transformDao = transformDB.getTransformDao(); // this is a method that Nick wrote
  }

  @Test
  public void insert() { // Gson evokes this method.  Room also invokes
    // this same method
    Transform transform = new Transform();
    String name = "Test Transform instance";
    transform.setName(name);
    List<Long> ids = transformDao.insert(transform);
    assertEquals(1, ids.size());
  }

  @Test
  public void select() { // Gson evokes this method.  Room also invokes
    // this same method
    Transform transform = new Transform();
    String name = "Test Transform instance";
    transform.setName(name);
    List<Long> ids = transformDao.insert(transform);
    Transform testTransform = transformDao.findById(ids.get(0));
    assertEquals(name, testTransform.getName());
  }

  @Test
  public void selectAll() { // This tests
    // this same method
    Transform[] transforms = new Transform[10];
    Random rng = new Random();
    for (int i = 0; i < transforms.length; ++i) {
      Transform transform = new Transform();
      transform.setName("Test Transform instance " + i);
      transforms[i] = transform;
    }
    transformDao.insert(transforms);
    Transform[] testApods = transformDao.findAll().toArray(new Transform[0]);
    assertArrayEquals(transforms, testApods);
  }


  @Test
  public void delete() { // Gson evokes this method.  Room also invokes
    // this same method
    Transform transform = new Transform();
    String name = "Test Transform instance";
    transform.setName(name);
    List<Long> ids = transformDao.insert(transform);
    Transform testApod = transformDao.findById(ids.get(0));
    int rowsDeleted = transformDao.delete(testApod);// delete and update return ints
    // to tell us how how many were deleted
    assertEquals(1, rowsDeleted);
    assertEquals(0, transformDao.findAll().size());
    assertTrue(transformDao.findAll().isEmpty());// different version of immediate
    // test above.
  }


  @After
  public void tearDown() throws Exception {
    transformDB.close();
  }

}
