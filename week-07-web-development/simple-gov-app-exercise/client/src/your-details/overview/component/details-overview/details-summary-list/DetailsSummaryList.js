import "./DetailsSummaryList.css";

export const DetailsSummaryList = props => {
    const {children} = props;

    return (
        <dl className="govuk-summary-list">
            {children}
        </dl>
    )
};

export const DetailsSummaryListRow = props => {
    const {children} = props;

    return (
        <div className="govuk-summary-list__row">
            {children}
        </div>
    )
};

export const DetailsSummaryListKey = props => {
    const {children} = props;

    return (
        <dt className="govuk-summary-list__key">
            {children}
        </dt>
    )
};

export const DetailsSummaryListValue = props => {
    const {children} = props;

    return (
        <dd className="govuk-summary-list__value">
            {children}
        </dd>
    )
};

export const DetailsSummaryListAction = props => {
    const {children} = props;

    return (
        <dd className="govuk-summary-list__actions">
            {children}
        </dd>
    )
};

