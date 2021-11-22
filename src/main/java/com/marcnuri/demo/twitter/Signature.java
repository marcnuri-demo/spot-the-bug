package com.marcnuri.demo.twitter;

import java.time.LocalDate;
import java.util.Objects;

public class Signature {

  private LocalDate date;
  private String name;
  private String city;
  private String country;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Signature signature = (Signature) o;
    return Objects.equals(date, signature.date) && Objects.equals(name, signature.name) && Objects.equals(city, signature.city) && Objects.equals(country, signature.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, name, city, country);
  }

  public static final class SignatureBuilder {
    private LocalDate date;
    private String name;
    private String city;
    private String country;

    private SignatureBuilder() {
    }

    public static SignatureBuilder aSignature() {
      return new SignatureBuilder();
    }

    public SignatureBuilder withDate(LocalDate date) {
      this.date = date;
      return this;
    }

    public SignatureBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public SignatureBuilder withCity(String city) {
      this.city = city;
      return this;
    }

    public SignatureBuilder withCountry(String country) {
      this.country = country;
      return this;
    }

    public Signature build() {
      Signature signature = new Signature();
      signature.setDate(date);
      signature.setName(name);
      signature.setCity(city);
      signature.setCountry(country);
      return signature;
    }
  }
}
