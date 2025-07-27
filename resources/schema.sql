drop table if exists claim_pay, policy_renew, cancellation, payments, policy_coverage, claim_category, claimtype, claim, coverage_category, premium_payment, endorsement, approved_policy_status, riskassessment, policy_customer, policytype, customer cascade;

create table customer (
    customerid bigint primary key generated always as identity,
    name varchar(255) not null,
    dob date not null,
    contact bigint not null,
    address varchar(255) not null,
    city varchar(255) not null,
    state varchar(255) not null,
    postal_code varchar(255) not null,
    country varchar(255) not null,
    gender varchar not null,
    email varchar(255) not null,
    national_id varchar(255) not null,
    background varchar(255) not null
);

create index idx_customer_contact_email_state on customer (contact, email, state);

create table policytype (
    policytypeid bigint primary key generated always as identity,
    policy_name varchar(255) not null,
    description text not null,
    risk_score bigint not null
);

create index idx_policytype_name on policytype (policy_name);

create table policy_customer (
    customer_policy_id bigint primary key generated always as identity,
    customer_id bigint not null,
    policytype_id bigint not null,
    status varchar not null,
    applieddate date not null,
    foreign key (customer_id) references customer(customerid),
    foreign key (policytype_id) references policytype(policytypeid)
);

create index idx_policy_customer on policy_customer (customer_id, policytype_id);

create table riskassessment (
    riskid bigint primary key generated always as identity,
    customerpolicy_id bigint not null,
    assessment_date date not null,
    risk_score bigint not null,
    risk_level varchar not null,
    risk_notes varchar(255) not null,
    last_updated_at timestamp not null,
    foreign key (customerpolicy_id) references policy_customer(customer_policy_id)
);

create index idx_risk_level on riskassessment (risk_level);

create table approved_policy_status (
    id bigint primary key generated always as identity,
    policycustomer_id bigint not null,
    issue_date date not null,
    expire_date date not null,
    coverage_limit bigint not null,
    grace_period varchar(255) not null,
    policy_amount bigint not null,
    premium_duration bigint not null,
    foreign key (policycustomer_id) references policy_customer(customer_policy_id)
);

create index idx_approved_policy_dates on approved_policy_status (issue_date, expire_date, coverage_limit);

create table endorsement (
    endorsement_id bigint primary key generated always as identity,
    policycustomer_id bigint not null,
    change_type varchar not null,
    old_value varchar(255) not null,
    new_value varchar(255) not null,
    issue_date date not null,
    update_date date not null,
    change_description varchar(255) not null,
    status varchar not null,
    foreign key (policycustomer_id) references policy_customer(customer_policy_id)
);

create index idx_endorsement_issue on endorsement (issue_date);

create table premium_payment (
    payement_id bigint primary key generated always as identity,
    customerpolicy_id bigint not null,
    due_date date not null,
    due_amount decimal(8,2) not null,
    status varchar not null,
    foreign key (customerpolicy_id) references policy_customer(customer_policy_id)
);

create index idx_due_date on premium_payment (due_date);

create table coverage_category (
    id bigint primary key generated always as identity,
    coverage_name bigint not null
);

create table claimtype (
    id bigint primary key generated always as identity,
    claimname varchar,
    content varchar
);

create table claim_category (
    id bigint primary key generated always as identity,
    claim_type bigint,
    policy_id bigint,
    foreign key (claim_type) references claimtype(id),
    foreign key (policy_id) references policytype(policytypeid)
);

create table claim (
    claimid bigint primary key generated always as identity,
    customerpolicy_id bigint not null,
    incident_date date not null,
    apply_date date not null,
    claim_type bigint not null,
    amountclaimed decimal(8,2) not null,
    status varchar not null,
    claim_note varchar(255) not null,
    foreign key (customerpolicy_id) references policy_customer(customer_policy_id),
    foreign key (claim_type) references claim_category(id)
);

create index idx_claim_applied on claim (apply_date, amountclaimed);

create table policy_coverage (
    id bigint primary key generated always as identity,
    policy_id bigint not null,
    coverage_id bigint not null,
    foreign key (policy_id) references approved_policy_status(id),
    foreign key (coverage_id) references coverage_category(id)
);

create table payments (
    id bigint primary key generated always as identity,
    payment_id bigint not null,
    paid_date date not null,
    paid_amount bigint not null,
    payment_method varchar not null,
    foreign key (payment_id) references premium_payment(payement_id)
);

create index idx_payments on payments (payment_id, paid_date);

create table claim_pay (
    id bigint primary key generated always as identity,
    claim_id bigint not null,
    amount_paid decimal(8,2) not null,
    payout_date date not null,
    foreign key (claim_id) references claim(claimid)
);

create table cancellation (
    id bigint primary key generated always as identity,
    customerpolicy_id bigint not null,
    cancellation_date date not null,
    accept_date date not null,
    cancellation_reason varchar(255) not null,
    refund_amount bigint not null,
    status varchar not null,
    foreign key (customerpolicy_id) references policy_customer(customer_policy_id)
);

create index idx_cancel_date on cancellation (cancellation_date);

create table policy_renew (
    id bigint primary key generated always as identity,
    change_date date not null,
    change_terms varchar(255) not null,
    customerpolicy_id bigint not null,
    foreign key (customerpolicy_id) references policy_customer(customer_policy_id)
);
