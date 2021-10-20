import React from "react";
import {Link} from "react-router-dom";

function HomepageLink(props) {
    const {header, name, route} = props;

    return (
        <div>
            <h3>{header}</h3>
            <Link to={route}>{name}</Link>
        </div>
    )
}

export {HomepageLink}