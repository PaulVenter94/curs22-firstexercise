package org.fasttrackit.curs22homework.vacation.domain;

public class Vacation {
    private final int id;
    private final String location;
    private final int price;
    private final int duration;

    public Vacation(int id, String location, int price, int duration) {
        this.id = id;
        this.location = location;
        this.price = price;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacation vacation = (Vacation) o;

        if (id != vacation.id) return false;
        if (price != vacation.price) return false;
        if (duration != vacation.duration) return false;
        return location != null ? location.equals(vacation.location) : vacation.location == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
