import react from 'react';
import {
    Alert,
    
} from 'reactstrap'

function Play(props){
    if(props.response.isLoading)
        return null;
    return(
        <Alert color={props.response.color}>
            <h4 className="alert-heading">{props.response.type}</h4>
            <hr />
            <p className="mb-0">
                {props.response.description!==null?props.response.description.split("\n").map(element=>{
                    return (
                        <>
                            <p>
                                {element}
                            </p>
                            <div style={{lineHeight:'10%'}}>
                                <br/>
                            </div>
                        </>
                    )
                }):null}
            </p>
        </Alert>
    )
}

export default Play;