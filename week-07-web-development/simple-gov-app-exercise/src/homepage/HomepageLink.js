import React from "react";
import {Link} from "react-router-dom";

function HomepageLink(props) {
    const {header, text, route} = props;

    return (
        <div>
            <h3>{header}</h3>
            <Link to={route}>{text}</Link>
        </div>
    )
}

export {HomepageLink}