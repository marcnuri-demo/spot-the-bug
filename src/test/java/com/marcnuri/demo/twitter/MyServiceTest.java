package com.marcnuri.demo.twitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.marcnuri.demo.twitter.MyService.retrieveIndividualSignatures;
import static com.marcnuri.demo.twitter.Signature.SignatureBuilder.aSignature;
import static org.assertj.core.api.Assertions.assertThat;

class MyServiceTest {

  @Test
  @DisplayName("retrieveIndividualSignatures, with null signatures, should return an empty Set")
  void retrieveIndividualSignaturesShouldHandleNPE() {
    final Set<Signature> result = retrieveIndividualSignatures(null);

    assertThat(result).isNotNull().isEmpty();
  }

  @Test
  @DisplayName("retrieveIndividualSignatures, with duplicate entries, should return a set of distinct signatures")
  void retrieveIndividualSignaturesShouldFilterDuplicates() {
    final List<Signature> rawSignatures = Arrays.asList(
      aSignature().withName("John Malkovich").withDate(LocalDate.of(1953, Month.DECEMBER, 9)).withCity("L.A.").withCountry("USA").build(),
      aSignature().withName("John Malkovich").withDate(LocalDate.of(1953, Month.DECEMBER, 9)).withCity("L.A.").withCountry("USA").build(),
      aSignature().withName("John Malkovich").withDate(LocalDate.of(1953, Month.DECEMBER, 9)).withCity("L.A.").withCountry("USSR").build(),
      aSignature().withName("Michael J. Fox").withAlias("Marty").withDate(LocalDate.of(1961, Month.JUNE, 9)).withCountry("USA").build(),
      aSignature().withName("Jeff Bridges").withDate(LocalDate.of(1949, Month.DECEMBER, 4)).withCountry("USA").build(),
      aSignature().withName("Jeff Bridges").withAlias("The Dude").withDate(LocalDate.of(1949, Month.DECEMBER, 4)).withCountry("USA").build()
    );

    final Set<Signature> result = retrieveIndividualSignatures(rawSignatures);

    assertThat(result).hasSize(5).extracting("name")
      .containsExactlyInAnyOrder("John Malkovich", "John Malkovich", "Michael J. Fox", "Jeff Bridges", "Jeff Bridges");
  }

  @Test
  @DisplayName("retrieveIndividualSignatures, with null entries, should return a set of distinct non-null signatures")
  void retrieveIndividualSignaturesShouldFilterNull() {
    final List<Signature> rawSignatures = Arrays.asList(
      null, null, null,
      aSignature().withName("John Malkovich").withDate(LocalDate.of(1953, Month.DECEMBER, 9)).withCity("L.A.").withCountry("USA").build(),
      null,
      aSignature().withName("Michael J. Fox").withDate(LocalDate.of(1961, Month.JUNE, 9)).withCountry("USA").build()
    );

    final Set<Signature> result = retrieveIndividualSignatures(rawSignatures);

    assertThat(result).hasSize(2).extracting("name")
      .containsExactlyInAnyOrder("John Malkovich", "Michael J. Fox");
  }
}
