package com.marcnuri.demo.twitter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyService {

  private MyService() {}

  /**
   * Retrieves a collection of individual/unique {@link Signature} objects for the provided list of raw signatures from
   * different sources.
   *
   * @param signatures collection of signatures to return a distinct Set
   * @return a Set containing the list of distinct/individual signatures
   */
  public static Set<Signature> retrieveIndividualSignatures(Collection<Signature> signatures) {
    if (signatures == null) {
      return Collections.emptySet();
    }
    return new HashSet<>(signatures);
  }
}
