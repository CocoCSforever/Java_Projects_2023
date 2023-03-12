package edu.neu.orgchart.search;

import edu.neu.orgchart.entities.Manager;

import java.math.BigDecimal;

/**
 * Filter builder allows you to build a filter to search with.  You can put as many criteria into a filter as you like.
 * All criteria are combined with an and, you can chain items together and complete with a build to return a filter.
 * Ex.
 * FilterBuilder filterBuilder = new FilterBuilder();
 * Filter filter = filterBuilder.city(employee.getAddress().getCity()).build()
 */
public class FilterBuilder {
    private Filter currentFilter = new Filter();

    public Filter build() {
        return currentFilter;
    }

    public FilterBuilder city(String city) {
        currentFilter.city = city;
        return this;
    }

    public FilterBuilder state(String state) {
        currentFilter.state = state;
        return this;
    }

    public FilterBuilder payLessThan(BigDecimal payLessThan) {
        currentFilter.payLessThan = payLessThan;
        return this;
    }

    public FilterBuilder payEqualTo(BigDecimal payEqualTo) {
        currentFilter.payEqualTo = payEqualTo;
        return this;
    }

    public FilterBuilder payGreaterThan(BigDecimal payGreaterThan) {
        currentFilter.payGreaterThan = payGreaterThan;
        return this;
    }

    public FilterBuilder bonusLessThan(double bonusLessThan) {
        currentFilter.bonusLessThan = bonusLessThan;
        return this;
    }

    public FilterBuilder bonusEqualTo(double bonusEqualTo) {
        currentFilter.bonusEqualTo = bonusEqualTo;
        return this;
    }

    public FilterBuilder bonusGreaterThan(double bonusGreaterThan) {
        currentFilter.bonusGreaterThan = bonusGreaterThan;
        return this;
    }

    public FilterBuilder reportsTo(Manager reportsTo) {
        currentFilter.reportsTo = reportsTo;
        return this;
    }

    /**
     * This class provides a filter to allow someone to filter employees based on location, base pay, bonus, and manager
     * All filters are an implicit and, there is no way to or these filters. If you pass a city and base pay, only employees
     * who live in that city and match the condition on base pay are returned.
     */
    public class Filter {
        private String city;
        private String state;
        private BigDecimal payLessThan;
        private BigDecimal payEqualTo;
        private BigDecimal payGreaterThan;
        private Double bonusLessThan;
        private Double bonusEqualTo;
        private Double bonusGreaterThan;
        private Manager reportsTo;

        public Filter() {
            bonusEqualTo = null;
            bonusGreaterThan = null;
            bonusLessThan = null;
        }
        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public BigDecimal getPayLessThan() {
            return payLessThan;
        }

        public BigDecimal getPayEqualTo() {
            return payEqualTo;
        }

        public BigDecimal getPayGreaterThan() {
            return payGreaterThan;
        }

        public Double getBonusLessThan() {
            return bonusLessThan;
        }

        public Double getBonusEqualTo() {
            return bonusEqualTo;
        }

        public Double getBonusGreaterThan() {
            return bonusGreaterThan;
        }

        public Manager getReportsTo() {
            return reportsTo;
        }
    }
}
